import {AxiosResponse} from "axios";
import Notificator, {NotificationType} from "./notificator";

const axiosModule = require('axios');

export interface ApiError {
    message: string
    type: NotificationType
}

export interface FormApiError extends ApiError {
    field: string
}

function isFormApiError(error: ApiError): boolean {
    return 'field' in error;
}

export interface ApiResponse {
    ok: boolean,
    status: string,
    errors: Array<ApiError>
}

export interface PaginationResponse<T> {
    items: T[],
    pages: number
}

export interface TableItem {
    id: number,
    isActive: boolean,
    delete: ((items: any[]) => void) | null
}

export interface Brand {
    id: number,
    name: string
}

export interface Product {
    id: number,
    name: string,
    article: string,
    barcode: string,
    brand: Brand
}

export interface User {
    id: number,
    email: string
}

export interface NewOffer {
    price: number,
    actualUntil: Date
}

export interface Offer extends NewOffer {
    id: number
}

export interface UserApiResponse extends ApiResponse {
    user: User
}

export function hasFormErrorWithField(errors: Array<ApiError>, field: string): boolean {
    for (const error of errors) {
        if (isFormApiError(error)) {
            const formError = <FormApiError> error;
            if (formError.field === field) {
                return true;
            }
        }
    }

    return false;
}

// Add a response interceptor
axiosModule.interceptors.response.use((response: AxiosResponse) => {
    const apiResponse = <ApiResponse> response.data;
    showNotificationsIfAvailable(apiResponse.errors);

    return response;
}, function (error: any) {
    const response = <AxiosResponse> error.response;
    const apiResponse = <ApiResponse> response.data;

    showNotificationsIfAvailable(apiResponse.errors);

    return error;
});

function showNotificationsIfAvailable(notifications: Array<ApiError>): void {
    for (const notification of notifications) {
        Notificator.notify(notification.message, <NotificationType> notification.type);
    }
}

export default axiosModule;
