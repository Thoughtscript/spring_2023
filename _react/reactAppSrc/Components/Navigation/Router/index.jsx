'use strict'

/**
 *  Router.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import CustomHeader from '../../Presentation/CustomHeader'
import './Router.css'
import { BASE_PATH, HOME_PATH, API_PATH } from '../../../Constants'

import LandingPage from '../../Presentation/LandingPage'
import PageNotFoundPage from '../../Presentation/PageNotFoundPage'
import CustomFooter from "../../Presentation/CustomFooter";

import { RailsPage } from '../../Stateful/RailsPage'

export default () =>
    <BrowserRouter>
        <div className="innerRouterWrapper">
            <CustomHeader />
            <Routes>
                <Route exact path={BASE_PATH} element={<LandingPage />} />
                <Route exact path={HOME_PATH} element={<LandingPage />} />
                <Route exact path={API_PATH} element={<RailsPage />} />
                <Route path="*" element={<PageNotFoundPage />} />
            </Routes>
            <CustomFooter />
        </div>
    </BrowserRouter>
