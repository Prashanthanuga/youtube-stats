import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ChannelService } from './channel.service';
import { Channel, NetworkSummary } from './channel.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App implements OnInit {

  channelNames: string[] = [];

  selectedChannel = '';

  selectedChannelData: Channel | null = null;

  networkSummary!: NetworkSummary;

  loading = false;

  constructor(private channelService: ChannelService) {}

  ngOnInit(): void {

    // Load dropdown names
    this.channelService.getChannelNames()
      .subscribe({

        next: (data) => {

          console.log('CHANNEL NAMES:', data);

          this.channelNames = data;
        },

        error: (err) => {

          console.error('DROPDOWN ERROR:', err);
        }
      });

    // Load network summary
    this.channelService.getNetworkSummary()
      .subscribe({

        next: (data) => {

          console.log('SUMMARY:', data);

          this.networkSummary = data;
        },

        error: (err) => {

          console.error('SUMMARY ERROR:', err);
        }
      });
  }

  onChannelSelect(): void {

    if (!this.selectedChannel) return;

    this.loading = true;

    this.channelService
      .getChannelByName(this.selectedChannel)
      .subscribe({

        next: (data) => {

          console.log('CHANNEL DATA:', data);

          this.selectedChannelData = data;

          this.loading = false;
        },

        error: (err) => {

          console.error('CHANNEL ERROR:', err);

          this.loading = false;
        }
      });
  }

  // International formatter
  formatNumber(value: number): string {

    if (value >= 1000000000) {
      return (value / 1000000000).toFixed(1) + 'B';
    }

    if (value >= 1000000) {
      return (value / 1000000).toFixed(1) + 'M';
    }

    if (value >= 1000) {
      return (value / 1000).toFixed(1) + 'K';
    }

    return value.toString();
  }

  // Indian formatter
  formatIndianNumber(value: number): string {

    if (value >= 10000000) {
      return (value / 10000000).toFixed(2) + ' Cr';
    }

    if (value >= 100000) {
      return (value / 100000).toFixed(2) + ' L';
    }

    return value.toLocaleString('en-IN');
  }
}