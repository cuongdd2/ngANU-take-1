define(['knockout', 'jquery', 'durandal/app', 'viewmodels/char'], function(ko, $, app, Char) {
    var context = {};

    var toggleSearch = function() {
        var toggle = arguments[0],
            searchText = context.view.search.text,
            searchButton = context.view.search.button;

        if (!toggle) {
            searchText.disabled = true;
            searchButton.disabled = true;
            searchButton.textContent = 'Searching...';
        }
        else {
            searchText.disabled = false;
            searchButton.disabled = false;
            searchButton.textContent = 'Search';
        }
    };

    context.openChars = ko.observableArray();
    context.activeChar = ko.observable(undefined);

    context.query = ko.observable('');
    context.search = function(context) {
        console.log('char-query');
        var query = context.query();

        var grep = $.grep(context.openChars(), function(role) {
            return (role.id == query) || (role.name == query)
        });
        if (grep.length) {
            context.activeChar(grep[0]);
            return;
        }

        toggleSearch();
        $.get('api/char/'+query)
            .fail(function() {
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
            .always(function() {
                toggleSearch(true);
            });
    };

    context.compositionComplete = function() {
        var el = document.getElementById('panel-char-container'),
            size = el.getBoundingClientRect(),
            height = window.innerHeight - size.top - 20;

        el.style.height = height + 'px';

        var searchView = document.getElementById('panel-char-search'),
            searchText = searchView.querySelector('input'),
            searchBtn  = searchView.querySelector('button');

        context.view = {
            root: el,
            search: {
                root: searchView,
                text: searchText,
                button: searchBtn
            }
        };
    };

    return context;
});