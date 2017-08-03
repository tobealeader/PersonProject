import React from 'react';
import './css/main.css'
var $ = require('jquery')
export default class Hello extends React.Component {
    dohand(){
        console.log(1);
        $('body').append('<div>hi</div>');
    }
    render() {
        console.log(2);
        this.dohand();
        return (
            <div>
                <h1>Hello world!!</h1>
            </div>
        );
    }
}