"use strict";
/*
* Node WebUSB
* Copyright (c) 2017 Rob Moran
*
* The MIT License (MIT)
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
Object.defineProperty(exports, "__esModule", { value: true });
var adapter_1 = require("./adapter");
/**
 * USB Interface
 */
var USBInterface = /** @class */ (function () {
    /**
     * @hidden
     */
    function USBInterface(init) {
        /**
         * Number of this interface
         */
        this.interfaceNumber = null;
        /**
         * Array of alternate interfaces
         */
        this.alternates = [];
        this._claimed = false;
        this._currentAlternate = 0;
        /**
         * @hidden
         */
        this._handle = null;
        this.interfaceNumber = init.interfaceNumber;
        this.alternates = init.alternates;
        this._handle = init._handle;
    }
    Object.defineProperty(USBInterface.prototype, "claimed", {
        /**
         * Whether this interface is claimed
         */
        get: function () {
            return this._claimed;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(USBInterface.prototype, "alternate", {
        /**
         * Return the current alternate interface
         */
        get: function () {
            var _this = this;
            return this.alternates.find(function (alternate) { return alternate.alternateSetting === _this._currentAlternate; });
        },
        enumerable: true,
        configurable: true
    });
    /**
     * @hidden
     */
    USBInterface.prototype.selectAlternateInterface = function (alternateSetting) {
        var _this = this;
        return adapter_1.adapter.selectAlternateInterface(this._handle, this.interfaceNumber, alternateSetting)
            .then(function () {
            _this._currentAlternate = alternateSetting;
        });
    };
    /**
     * @hidden
     */
    USBInterface.prototype.claimInterface = function () {
        var _this = this;
        return adapter_1.adapter.claimInterface(this._handle, this.interfaceNumber)
            .then(function () {
            _this._claimed = true;
        });
    };
    /**
     * @hidden
     */
    USBInterface.prototype.releaseInterface = function () {
        var _this = this;
        return adapter_1.adapter.releaseInterface(this._handle, this.interfaceNumber)
            .then(function () {
            _this._claimed = false;
        });
    };
    /**
     * @hidden
     */
    USBInterface.prototype.reset = function () {
        this._currentAlternate = 0;
    };
    return USBInterface;
}());
exports.USBInterface = USBInterface;

//# sourceMappingURL=interface.js.map
