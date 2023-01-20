'use strict'

/**
 *  Navigation Menu.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import { Link } from 'react-router-dom'
import { API_PATH, BASE_PATH, HOME_PATH } from '../../../Constants'

let w = window.location.pathname

export class Menu extends React.Component {
    constructor(props) {
        super(props);
        this.home = React.createRef();
        this.api = React.createRef();
    }

    render() {
        return (
            <nav>
                <ul>
                    <li className="home">
                        <Link onClick={() => {
                            w = BASE_PATH;
                            this.home.current.className = 'active';
                            this.api.current.className = 'inactive';
                        }}
                            ref={this.home}
                            className={(w === BASE_PATH || w === HOME_PATH) ? 'active' : 'inactive'}
                            to={BASE_PATH}>Home</Link>
                    </li>

                    <li className="api">
                        <Link onClick={() => {
                            w = BASE_PATH;
                            this.home.current.className = 'inactive';
                            this.api.current.className = 'active';
                        }}
                            ref={this.api}
                            className={(w === API_PATH) ? 'active' : 'inactive'}
                            to={API_PATH}>API</Link>
                    </li>
                </ul>
            </nav>)
    }
}