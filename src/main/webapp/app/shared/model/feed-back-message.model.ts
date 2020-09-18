import { Moment } from 'moment';
import { FeedBackMessageType } from 'app/shared/model/enumerations/feed-back-message-type.model';

export interface IFeedBackMessage {
  id?: number;
  createDate?: Moment;
  author?: string;
  type?: FeedBackMessageType;
  content?: string;
}

export class FeedBackMessage implements IFeedBackMessage {
  constructor(
    public id?: number,
    public createDate?: Moment,
    public author?: string,
    public type?: FeedBackMessageType,
    public content?: string
  ) {}
}
