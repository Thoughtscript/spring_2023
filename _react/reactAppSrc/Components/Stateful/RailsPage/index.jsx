'use strict'

/**
 *  RailsPage page.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import UnsplashSection from '../../Presentation/UnsplashSection'
import './RailsPage.css'
import YouTubeComponent from "../../Presentation/YouTubeComponent";
import { asyncGet } from '../../../Helpers/Xhr/Get'
import { RAILS_API_URL } from '../../../Constants'

export class RailsPage extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            dinos: [],
            ...this.props
        }
    }

    componentDidMount() {
        // Add IndexDB check here.
        // Check time and empty array.
        try {
            asyncGet(RAILS_API_URL).then(success => {
                this.setState({
                    dinos: JSON.parse(success)
                })
            })
        } catch (ex) {
            console.log(ex)
        }
    }

    render() {
        const { dinos } = this.state

        return (
            <main className="landingPage">
                <UnsplashSection photo={'1591858219137-84737f6e8a67'} ixid={'eyJhcHBfaWQiOjEyMDd9'} />
                <div className="content">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                (dinos).map(p =>
                                    <tr key={p.id}>
                                        <th>{p.id}</th>
                                        <th>{p.name}</th>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <YouTubeComponent className="more" url={"https://www.youtube.com/embed/W93XyXHI8Nw"} />
            </main>
        )
    }
}