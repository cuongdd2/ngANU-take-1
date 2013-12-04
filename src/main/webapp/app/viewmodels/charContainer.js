define(['knockout', 'jquery', 'durandal/app', 'viewmodels/char'], function(ko, $, app, Char) {
    var context = {};

    context.openChars = ko.observableArray();
    context.activeChar = ko.observable(undefined);

    context.query = ko.observable('');
    context.search = function(context, event) {
        console.log('char-query');
        var query = context.query();
        $.get('api/char/'+query)
            .fail(function(http) {
                app.showMessage(
                    'Cannot find character with id or name of ' + query,
                    'Not Found'
                )
            })
            .done(function(xmlRole) {
                var c = new Char(xmlRole);
                context.openChars.push(c);
                context.activeChar(c);
            })
    };

    return context;
})