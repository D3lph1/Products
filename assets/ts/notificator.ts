import store from './store'

export interface Notification {
    createdAt: number,
    type: string,
    message: string
}

export enum NotificationType {
    Success = 'success',
    Info = 'info',
    Warning = 'warning',
    Danger = 'danger'
}

export default class Notificator {
    public static success(message: string): void {
        this.notify(message, NotificationType.Success);
    }

    public static info(message: string): void {
        this.notify(message, NotificationType.Info)
    }

    public static warning(message: string): void {
        this.notify(message, NotificationType.Warning)
    }

    public static danger(message: string): void {
        this.notify(message, NotificationType.Danger)
    }

    public static notify(message: string, type: NotificationType): void {
        store.commit('notify', {type, message});
    }

    public static clear(): void {
        store.commit('clearNotifications');
    }
}
