window.com_company_workshop_web_ext_ChameleonJsSnippetInjector = function() {
    var self = this;
    self.init = function(userId, userEmail, userName, userRoles) {
        var script = document.createElement("script");
        script.setAttribute("type", "text/javascript");
        // var scriptText = 'alert("userId: ' + userId + '; userRole: ' + userRoles + '");';
        scriptText += '!function(t,n,o){var a="chmln",e="adminPreview",c="setup identify alias track clear set show on off custom help _data".split(" ");if(n[a]||(n[a]={}),n[a][e]&&(n[a][e]=!1),!n[a].root){n[a].accountToken=o,n[a].location=n.location.href.toString(),n[a].now=new Date;for(var s=0;s<c.length;s++)!function(){var t=n[a][c[s]+"_a"]=[];n[a][c[s]]=function(){t.push(arguments)}}();var i=t.createElement("script");i.src="https://fast.trychameleon.com/messo/"+o+"/messo.min.js",i.async=!0,t.head.appendChild(i)}}(document,window,"S9m04zjA3YmE7qpw3t1xS5S9sHs7VTVz6EypzPUBJI5Jx5-1GnaTs-B00iCKXH0iRe1tfG");';
        scriptText += 'chmln.identify("' + userId + '", {email: "' + userEmail + '", name: "' + userName + '", role: "' + userRoles + '"});';
        script.text = scriptText;
        document.getElementsByTagName("head")[0].appendChild(script);
    }
};
