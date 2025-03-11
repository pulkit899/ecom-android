function cookieStatus()
{
if(document.cookie.indexOf('s_nr30')>-1)
{
    function getNewRepeatCookie(cname)
    {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for(let i = 0; i <ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function setNewRepeatCookie(cname)
    {
        var timestamp = Date.now();
        var userStatus="Repeat"

        var date = new Date()
        var expiry=date.setTime(date.getTime() + (30 * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toUTCString();

        var finalValue = timestamp+"-"+userStatus

        document.cookie = cname+"="+finalValue+";"+expires+";path=/";

    }

    var cookieValue = getNewRepeatCookie('s_nr30');
    var status = cookieValue.split('-')[1];

    _satellite.setVar("userState", status)

    setNewRepeatCookie('s_nr30', status);
}

else
{
    function setNewRepeatCookie(cname, cvalue)
    {
        var timestamp = Date.now();

        var date = new Date()
        var expiry=date.setTime(date.getTime() + (30 * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toUTCString();

        var finalValue = timestamp+"-"+cvalue

        document.cookie = cname+"="+finalValue+";"+expires+";path=/";

    }
    setNewRepeatCookie('s_nr30', "New");
}
}
