var isBottomLocked = true;

$(document).ready(function() {

    joinUser();
    sendHeartBeat();
    getPastMsg();
    getMsg();
    eventHandlers();

    //Syntax highligheter config
    SyntaxHighlighter.config.stripBrs = true;
});

function eventHandlers() {
    //Send message event handler
    $('.chat-text').on("keypress", function(e) {
        if (e.which === 13 && e.shiftKey) {

        } else if (e.keyCode === 13) {
            var msg = $('.chat-text').val();
            if (msg.charAt(0) === '/') {
                sndCmd();
            } else {
                sndMsg();
            }
            return false;
        }
    });

    //Timestamp Event handler
    {
        var timer;
        $('body').on('mouseenter', '.username', function() {
            var usrNameEle = $(this);
            timer = setTimeout(function() {
                var timeMilli = usrNameEle.prev(".tsdata").text();
                console.log(timeMilli);
                timeMilli = getReadableTimeStamp(timeMilli);
                console.log(timeMilli);
                usrNameEle.tooltip({
                    'html': false,
                    'trigger': 'manual',
                    'placement': 'top',
                    'title': timeMilli
                });
                usrNameEle.tooltip('show');
            }, 1000);
        }).on('mouseleave', '.username', function() {
            clearTimeout(timer);
            $(this).tooltip('destroy');
        });
    }

    //Chat options model event handler
    $('.chat-options').click(function() {
        $('#myModal').modal('show');
    });

    //Insert Code event handler
    $('.post-mime-code').click(function() {
        var code = escapeHtml($('.mime-code').val());
        sndMsg('<pre class="brush: ' + $('.mime-code-lang').val() + '">' + code + '</pre>');
        $('#myModal').modal('hide');
    });
}

$(window).scroll(function() {
    if ($(window).scrollTop() + $(window).height() === getDocHeight()) {
        isBottomLocked = true;
    } else {
        isBottomLocked = false;
    }
});


function getDocHeight() {
    var D = document;
    return Math.max(
            Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),
            Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),
            Math.max(D.body.clientHeight, D.documentElement.clientHeight)
            );
}

function goToBottom() {
    if (isBottomLocked) {
        $("html, body").animate({scrollTop: $(document).height()}, 0);
    }
}

function escapeHtml(text) {
    return text
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
}

function joinUser() {
    $.getJSON("api/v1/chat", {
        action: "joinUser",
        authid: $.cookie('authid'),
        roomid: 1
    }).done(function(json) {
        if (json.success) {
            console.log(json.result);
        } else {
            console.log(json.error);
        }
    });
}

function sndMsg(msg) {
    if (!msg) {
        msg = $('.chat-text').val();
    }

    $.post("api/v1/chat", {
        action: "sndMsg",
        roomId: 1,
        authid: $.cookie('authid'),
        msg: msg
    }).done(function(json) {
        if (json.success) {
            console.log(json.result);
        } else {
            console.log(json.error);
        }
    });

//    $.getJSON("api/v1/chat", {
//        action: "sndMsg",
//        roomId: 1,
//        authid: $.cookie('authid'),
//        msg: msg
//    }).done(function(json) {
//        if (json.success) {
//            console.log(json.result);
//        } else {
//            console.log(json.error);
//        }
//    });
    $('.chat-text').val('');
}

function sndCmd() {
    if ($('.chat-text').val() === '/help' || $('.chat-text').val() === '/') {
        listCommands();
        return;
    }
    $.getJSON("api/v1/chat", {
        action: "sndCmd",
        roomId: 1,
        authid: $.cookie('authid'),
        cmd: $('.chat-text').val()
    }).done(function(json) {
        if (json.success) {
            if (json.result.type === 'STRING') {
                displayPreOutput(json.result.result);
            }
            console.log(json.result);
        } else {
            console.log(json.error);
        }
    });
    $('.chat-text').val('');
}

function getMsg() {
    $.ajax({
        url: "api/v1/chat",
        type: "GET",
        data: {
            action: "getMsg",
            roomId: 1,
            authid: $.cookie('authid')
        },
        dataType: "json",
        timeout: 30000,
        complete: function() {
            goToBottom();
            if ($.cookie('authid') !== undefined) {
                getMsg();
            }
        },
        success: function(json) {
            notifyUser();
            if (json.success) {
                for (var i = 0; i < json.result.length; i++) {
                    var msg = json.result[i];
                    displayMessage(msg.username, msg.msg, msg.timestamp);
                }
            }
        }
    });
}

function getPastMsg() {
    $.ajax({
        url: "api/v1/chat",
        type: "GET",
        data: {
            action: "getPstMsg",
            roomId: 1,
            authid: $.cookie('authid'),
            n: 300
        },
        dataType: "json",
        timeout: 30000,
        complete: function() {
            goToBottom();
        },
        success: function(json) {
            if (json.success) {
                for (var i = 0; i < json.result.length; i++) {
                    var msg = json.result[i];
                    displayMessage(msg.username, msg.msg, msg.timestamp);
                }
            }
        }
    });
}

function sleep(millis, callback) {
    setTimeout(function()
    {
        callback();
    }, millis);
}

function displayPreOutput(msg) {
    $('.chat-box').append('<pre>' + msg + '</pre>');
    $('.chat-text').val('');
    goToBottom();
}

function notifyUser() {
    //Alert User in title
    $.titleAlert("(1) New message", {
        requireBlur: true,
        stopOnFocus: true,
        interval: 1000
    });

    //Alert User
    if (!document.hasFocus()) {
        //Play sounds
        var soundHandle = document.getElementById('soundHandle');
        soundHandle.src = 'assets/sound/notification.mp3';
        soundHandle.play();
    }
}

function sendHeartBeat() {
    $.getJSON("api/v1/chat", {
        action: "heartbeat",
        roomid: 1,
        authid: $.cookie('authid')
    }).done(function(json) {
        if (json.success) {
            console.log(json.result);
        } else {
            console.log(json.error);
        }
    }).always(function() {
        sleep(30000, sendHeartBeat);
    });
}

function listCommands() {
    var helptext = "Available Commands:\n";
    helptext += "  /afk\t\tToggles AFK mode On/Off\n";
    helptext += "  /list\t\tDisplays all the users connected\n";
    helptext += "  /help\t\tLists all available commands\n";
    helptext += "  /admin help\tLists all admin available commands";
    displayPreOutput(helptext);
}

function getReadableTimeStamp(milli_timestamp) {
    return moment(Number(milli_timestamp)).format('ddd, h:mmA');
}

function displayMessage(username, msg, timestamp) {
    //Ordered with priority
    var code = getCode(msg);
    var youtube = getYoutubeEmbed(msg);
    var image = getImageEmbed(msg);
    var link = getLinkEmbed(msg);
    

    //Pre Append
    var msgRender = '<span class="tsdata">' + timestamp + '</span>' + '<b class="username">' + username + ':</b><div class="msg-text">';
    if(code !== null) {
        msgRender += code + '</div><div class="clear-float"></div>';
    } else if (youtube !== null) {
        msgRender += link + '</div><div class="clear-float"></div>' + youtube;
    } else if (image !== null) {
        msgRender += link + '</div><div class="clear-float"></div>' + image;
    } else if (link !== null) {
        msgRender += link + '</div><div class="clear-float"></div>';
    } else {
        msg = msg.replace(/\r\n|\r|\n/g, "<br />");
        msgRender += msg + '</div><div class="clear-float"></div>';
    }
    $('.chat-box').append(msgRender);
    SyntaxHighlighter.highlight();
}

function getYoutubeEmbed(msg) {
    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
    var match = msg.match(regExp);
    if (match && match[7].length == 11) {
        return '<blockquote>\
<iframe class="mime" width="400" height="225" src="https://www.youtube.com/embed/' + match[7] + '" frameborder="0" allowfullscreen></iframe>\n\
</blockquote>';
    } else {
        return null;
    }
}

function getImageEmbed(msg) {
    var url = msg.match(/(((ftp|https?):\/\/)[\-\w@:%_\+.~#?,&\/\/=]+)|((mailto:)?[_.\w-]+@([\w][\w\-]+\.)+[a-zA-Z]{2,3})/g);
    if (url !== null && url[0].match(/(jpg|png|gif)/g) != null) {
        return '<blockquote>\
        <a href="' + url + '" data-lightbox="image-1"><img src="' + url + '" width="500px" onload="goToBottom()"/></a></blockquote>'
    } else {
        return null;
    }
}

function getLinkEmbed(msg) {
    var url = msg.match(/((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)/);
    if (url !== null) {
        var link = '<a target="_blank" href="' + url[0] + '">' + url[0] + '</a>';
        return msg.replace(url[0], link);
    } else {
        return null;
    }
}

function getCode(msg) {
    if (!msg.match(/^<pre class="brush:/) || !msg.match(/pre>/)) {
        return null;
    }
    var htmlRender = "<div class='bubble'><div class='inner'>";
    htmlRender += msg;
    htmlRender += "</div></div>";

    return htmlRender;
}
