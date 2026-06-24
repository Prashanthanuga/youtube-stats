package com.tamada.api.controller;

import com.tamada.api.model.Channel;
import com.tamada.api.service.ChannelService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/channels")

@CrossOrigin(origins = "http://localhost:4200")

public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    // GET: Channel names for dropdown
    @GetMapping("/names")
    public List<String> getChannelNames() {
        return channelService.getChannelNames();
    }

    // GET: All channel data
    @GetMapping("/all")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    // GET: Single channel by name
    @GetMapping("/{name}")
    public Channel getChannelByName(@PathVariable String name) {
        return channelService.getChannelByName(name);
    }

    // GET: Network summary KPIs
    @GetMapping("/summary/network")
    public Map<String, Object> getNetworkSummary() {
        return channelService.getNetworkSummary();
    }
}