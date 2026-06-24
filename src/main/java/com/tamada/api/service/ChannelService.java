package com.tamada.api.service;

import com.tamada.api.model.Channel;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    private final List<Channel> channels = new ArrayList<>();

    @PostConstruct
    public void init() {

        channels.addAll(List.of(

                new Channel(
                        "Wirally",
                        4200000,
                        1500000000L,
                        1800,
                        12.3,
                        22.5,
                        81.2
                ),

                new Channel(
                        "Dhethadi",
                        2600000,
                        900000000L,
                        1200,
                        10.5,
                        26.3,
                        85.0
                ),

                new Channel(
                        "Think Chey",
                        613000,
                        320000000L,
                        420,
                        8.5,
                        11.2,
                        85.2
                ),

                new Channel(
                        "Filmy Focus",
                        1500000,
                        780000000L,
                        950,
                        9.4,
                        18.8,
                        74.5
                ),

                new Channel(
                        "Mahathalli",
                        2100000,
                        640000000L,
                        870,
                        7.1,
                        14.6,
                        72.4
                ),

                new Channel(
                        "Kaasko",
                        950000,
                        410000000L,
                        560,
                        6.8,
                        12.5,
                        71.5
                ),

                new Channel(
                        "Wirally Tamil",
                        780000,
                        290000000L,
                        480,
                        5.2,
                        8.4,
                        68.4
                ),

                new Channel(
                        "Wirally Originals",
                        1200000,
                        510000000L,
                        730,
                        7.3,
                        13.7,
                        76.9
                ),

                new Channel(
                        "Filmy Focus Originals",
                        430000,
                        180000000L,
                        210,
                        3.4,
                        5.8,
                        65.2
                ),

                new Channel(
                        "Filmy Focus Shorts",
                        320000,
                        140000000L,
                        340,
                        4.6,
                        6.2,
                        69.7
                )

        ));
    }

    // Returns all channels
    public List<Channel> getAllChannels() {
        return channels;
    }

    // Returns dropdown names
    public List<String> getChannelNames() {

        return channels.stream()
                .map(Channel::getChannelName)
                .sorted()
                .collect(Collectors.toList());
    }

    // Returns one channel
    public Channel getChannelByName(String name) {

        return channels.stream()
                .filter(channel ->
                        channel.getChannelName()
                                .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Returns dashboard summary
    public Map<String, Object> getNetworkSummary() {

        Map<String, Object> summary = new LinkedHashMap<>();

        long totalSubscribers = channels.stream()
                .mapToLong(Channel::getSubscribers)
                .sum();

        long totalViews = channels.stream()
                .mapToLong(Channel::getTotalViews)
                .sum();

        int totalVideos = channels.stream()
                .mapToInt(Channel::getVideos)
                .sum();

        double totalRevenue = channels.stream()
                .mapToDouble(Channel::getRevenue)
                .sum();

        double totalMonthlyViews = channels.stream()
                .mapToDouble(Channel::getMonthlyViews)
                .sum();

        double avgProductivity = channels.stream()
                .mapToDouble(Channel::getProductivityScore)
                .average()
                .orElse(0);

        Optional<Channel> topSubscribers = channels.stream()
                .max(Comparator.comparingLong(Channel::getSubscribers));

        Optional<Channel> topRevenue = channels.stream()
                .max(Comparator.comparingDouble(Channel::getRevenue));

        Optional<Channel> topProductivity = channels.stream()
                .max(Comparator.comparingDouble(Channel::getProductivityScore));

        Optional<Channel> lowProductivity = channels.stream()
                .min(Comparator.comparingDouble(Channel::getProductivityScore));

        summary.put("totalChannels", channels.size());

        summary.put("totalSubscribers", totalSubscribers);

        summary.put("totalViews", totalViews);

        summary.put("totalVideosPublished", totalVideos);

        summary.put(
                "totalEstMonthlyViewsM",
                Math.round(totalMonthlyViews * 10.0) / 10.0
        );

        summary.put(
                "totalMonthlyRevenueCr",
                Math.round((totalRevenue / 100.0) * 10.0) / 10.0
        );

        summary.put(
                "avgProductivityScore",
                Math.round(avgProductivity * 10.0) / 10.0
        );

        summary.put(
                "highestSubscribersChannel",
                topSubscribers.map(Channel::getChannelName)
                        .orElse("N/A")
        );

        summary.put(
                "highestRevenueChannel",
                topRevenue.map(Channel::getChannelName)
                        .orElse("N/A")
        );

        summary.put(
                "bestProductivityChannel",
                topProductivity.map(Channel::getChannelName)
                        .orElse("N/A")
        );

        summary.put(
                "needsAttentionChannel",
                lowProductivity.map(Channel::getChannelName)
                        .orElse("N/A")
        );

        return summary;
    }
}