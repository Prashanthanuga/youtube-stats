export interface Channel {

  channelName: string;

  subscribers: number;

  totalViews: number;

  videos: number;

  monthlyViews: number;

  revenue: number;

  productivityScore: number;
}

export interface NetworkSummary {

  totalChannels: number;

  totalSubscribers: number;

  totalViews: number;

  totalVideosPublished: number;

  totalEstMonthlyViewsM: number;

  totalMonthlyRevenueCr: number;

  avgProductivityScore: number;

  highestSubscribersChannel: string;

  highestRevenueChannel: string;

  bestProductivityChannel: string;

  needsAttentionChannel: string;
}