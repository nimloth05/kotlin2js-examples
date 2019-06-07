(function (_, Kotlin) {
  'use strict';
  function addNode() {
    var tmp$;
    var span = document.createElement('span');
    span.textContent = 'Hello NodeJs Toolchain';
    (tmp$ = document.body) != null ? tmp$.appendChild(span) : null;
  }
  _.addNode = addNode;
  return _;
}(module.exports, require('kotlin')));

//# sourceMappingURL=kotlinApp.js.map
