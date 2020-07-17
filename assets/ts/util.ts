export function formatDate(date: Date|string): string {
    if (typeof date === 'string') {
        date = new Date(date);
    }

    // Time with leading zero.
    const time = (date.getHours() < 10 ? '0' : '') + date.getHours() + ':' +
        (date.getMinutes() < 10 ? '0' : '') + date.getMinutes() + ':' +
        (date.getSeconds() < 10 ? '0' : '') + date.getSeconds();

    // Date with leading zero
    const _date = (date.getDate() < 10 ? '0' : '') + date.getDate() + '/' +
        ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1) + '/';

    return `${time} ${_date}${date.getFullYear()}`;
}
