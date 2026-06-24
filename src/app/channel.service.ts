import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Channel, NetworkSummary } from './channel.model';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  private apiUrl = 'http://localhost:8080/api/channels';

  constructor(private http: HttpClient) {}

  // Dropdown names
  getChannelNames(): Observable<string[]> {

    return this.http.get<string[]>(
      `${this.apiUrl}/names`
    );
  }

  // All channels
  getAllChannels(): Observable<Channel[]> {

    return this.http.get<Channel[]>(
      `${this.apiUrl}/all`
    );
  }

  // Single channel
  getChannelByName(name: string): Observable<Channel> {

    return this.http.get<Channel>(
      `${this.apiUrl}/${name}`
    );
  }

  // Network summary
  getNetworkSummary(): Observable<NetworkSummary> {

    return this.http.get<NetworkSummary>(
      `${this.apiUrl}/summary/network`
    );
  }
}