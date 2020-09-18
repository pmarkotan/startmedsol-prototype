import { Moment } from 'moment';
import { AnnouncementLocation } from 'app/shared/model/enumerations/announcement-location.model';
import { AnnouncementType } from 'app/shared/model/enumerations/announcement-type.model';

export interface IAnnouncement {
  id?: number;
  publishingDate?: Moment;
  expireDate?: Moment;
  location?: AnnouncementLocation;
  type?: AnnouncementType;
  content?: string;
}

export class Announcement implements IAnnouncement {
  constructor(
    public id?: number,
    public publishingDate?: Moment,
    public expireDate?: Moment,
    public location?: AnnouncementLocation,
    public type?: AnnouncementType,
    public content?: string
  ) {}
}
