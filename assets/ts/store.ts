import Vue from 'vue'
import Vuex from 'vuex'
import {Notification, NotificationType} from './notificator'

Vue.use(Vuex);

interface User {
    id: number;
    email: string;
}

interface State {
    user: User|null;
    notifications: Array<Notification>;
}

export default new Vuex.Store({
    state: <State> {
        user: null,
        notifications: []
    },
    mutations: {
        authenticate(state: State, payload: {user: User}): void {
            state.user = payload.user;
        },
        logout(state: State): void {
            state.user = null;
        },
        notify(state: State, payload: {type: NotificationType, message: string}): void {
            const notification: Notification = {
                createdAt: Date.now(),
                type: payload.type,
                message: payload.message
            };

            state.notifications.unshift(notification);
        },
        clearNotifications(state: State): void {
            state.notifications = [];
        }
    },
    getters: {
        isAuth(state: State): boolean {
            return state.user !== null;
        },
        getUser(state: State): User {
            if (state.user === null) {
                throw new Error("User not authenticated");
            }

            return state.user;
        }
    }
});
