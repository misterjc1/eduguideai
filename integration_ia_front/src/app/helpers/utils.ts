export function numStr(a, b) {
    a = '' + a;
    b = b || ' ';
    var c = '',
        d = 0;
    while (a.match(/^0[0-9]/)) {
        a = a.substr(1);
    }
    for (var i = a.length - 1; i >= 0; i--) {
        c = (d != 0 && d % 3 == 0) ? a[i] + b + c : a[i] + c;
        d++;
    }
    return c;
}

export function formatMyDate(date: Date): string {
    const dateC = new Date(date);

    const dd = String(dateC.getDate()).padStart(2, '0');
    const mm = String(dateC.getMonth() + 1).padStart(2, '0');
    const yyyy = dateC.getFullYear();

    console.log("dd-mm-yyyy", dd + '/' + mm + '/' + yyyy);
    return dd + '/' + mm + '/' + yyyy;
}
