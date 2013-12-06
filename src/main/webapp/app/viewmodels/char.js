define(['knockout'], function(ko) {
    return function(charBean) {
        $.extend(this,charBean);
        window.d = this;

        this.getGenderFAClass = function() { return (this.base.gender == 0) ? 'fa-male' : 'fa-female' }

        this.save = function() {

        }
    }
})