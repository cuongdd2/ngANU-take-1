define(['knockout', 'lodash'], function(ko, _) {
    return function(charBean) {
        $.extend(this,charBean);
        var self = this;

        this.getGenderFAClass = function() { return (this.base.gender == 0) ? 'fa-male' : 'fa-female' };

        var inventories = [];
        this.getInventories = function() {
            if (inventories.length) return inventories;

            var cap = self.pocket.capacity,
                items = self.pocket.items;

            for (var i = 0; i <= cap; i++) {
                var slot = _.find(items, { pos: i });
                console.log('slot',i,slot);
                if (slot)
                    inventories[i] = slot;
                else
                    inventories[i] = null;
            }

            return inventories;
        };

        this.save = function() {

        };
    }
})