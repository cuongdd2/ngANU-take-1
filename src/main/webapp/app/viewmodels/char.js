define(['knockout'], function(ko) {
    return function(xmlRole) {
        this.isDirty = false;

        this.xmlRole = xmlRole;

        var base = xmlRole.querySelector('base');
        this.id = base.querySelector('variable[name=id]').textContent;
        this.name = base.querySelector('variable[name=name]').textContent;

        var status = xmlRole.querySelector('status');
        this.level = status.querySelector('variable[name=level]').textContent;
        this.hp = status.querySelector('variable[name=hp]').textContent;
        this.mp = status.querySelector('variable[name=mp]').textContent;

        var _exp = status.querySelector('variable[name=exp]');
        this.exp = ko.observable(_exp.textContent);
        this.exp.subscribe(function(value) {
            _exp.textContent = value;
        });

        console.log(this.id);

        this.save = function() {

        }
    }
})