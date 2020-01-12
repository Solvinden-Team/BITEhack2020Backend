package org.solvinden.bitehack2020.backend.stats;

import org.solvinden.bitehack2020.backend.dto.RoomStats;
import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.solvinden.bitehack2020.backend.repository.PeopleInRoomRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class StatsCalculator {

    private final PeopleInRoomRepository peopleInRoomRepository;

    public StatsCalculator(PeopleInRoomRepository peopleInRoomRepository) {
        this.peopleInRoomRepository = peopleInRoomRepository;
    }

    class Period {
        private Time start;
        private Time end;
        public Period(Time start, Time end) {
            this.start = start;
            this.end = end;
        }
        public boolean contains(Time t) {
            return t.after(this.start) && t.before(this.end);
        }
    }

    private List<Period> generatePeriods() {
        List<Period> p = new LinkedList<>();
        for(int h = 0; h < 24; h++) {
            for(int m = 0; m < 60; m+=15) {
                Time s = Time.valueOf(LocalDateTime.of(1, 1, 1, h, m, 0).toLocalTime());
                Time e = m <= 30 ? Time.valueOf(LocalDateTime.of(1, 1, 1, h, m + 15, 0).toLocalTime()) : Time.valueOf(LocalDateTime.of(1, 1, 1, h, 59, 59).toLocalTime());
                p.add(new Period(s, e));
            }
        }
        return p;
    }

    public RoomStats calculate(int roomId) {
        List<PeopleInRoom> peoplesInRoom = peopleInRoomRepository.getAllByRoomId(roomId);
        List<List<PeopleInRoom>> days = new LinkedList<>();
        LocalDate currentDate = LocalDate.now();
        final List<PeopleInRoom> peoplesInRoomOld = peoplesInRoom.stream().filter(p -> new Date(p.timestamp).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(currentDate)).collect(Collectors.toList());
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(day -> days.add(peoplesInRoomOld.stream().filter(p -> isDaysBefore(day + 1, p.timestamp)).collect(Collectors.toList())));

        List<Period> periods = generatePeriods();
        Map<Period, Double> periodAverage = new HashMap<>();
        Map<Period, Double> periodSum = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            List<PeopleInRoom> pir = days.get(i);
            for(Period p : periods) {
                int peopleSum = 0;
                int subperiods = 0;
                for(PeopleInRoom people : pir) {
                    peopleSum += people.peopleCount;
                    subperiods++;
                }
                double avg = (float)peopleSum / (float)subperiods;
                periodSum.put(p, periodSum.getOrDefault(p, 0.0) + avg);
            }
        }
        for(Period k: periodSum.keySet()) {
            periodAverage.put(k, periodSum.get(k) / 10.0);
        }
        int currentPeriod = currentPeriodIndex(periods, Time.valueOf(LocalTime.now()));
        int currentPeople = peoplesInRoom.get(peoplesInRoom.size() - 1).peopleCount;
        List<Integer> nextPeriods = Arrays.asList(currentPeriod, currentPeriod + 1, currentPeriod + 2, currentPeriod + 3);
        List<Double> nextPeopleMean = nextPeriods.stream().map(i -> periodAverage.get(periods.get(i))).collect(Collectors.toList());
        int dp = currentPeople - nextPeopleMean.get(0).intValue();
        List<Integer> nextPeople = nextPeopleMean.stream().map(x -> x.intValue() + dp).collect(Collectors.toList());
        return new RoomStats(RoomLoad.fromInt(dp), nextPeople);
    }

    private int currentPeriodIndex(List<Period> periods, Time time) {
        for(int i = 0; i < periods.size(); i++) {
            if(periods.get(i).contains(time)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isDaysBefore(int daysCount, long timestamp) {
        LocalDate currentDate = LocalDate.now();
        LocalDate ld = new Date(timestamp).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ld.isBefore(currentDate.minusDays(daysCount)) && ld.isBefore(currentDate.minusDays(daysCount + 1));
    }
}
