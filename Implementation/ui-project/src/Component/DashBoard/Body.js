import React, { useEffect } from 'react';
import Header from './Header';
import SideBar from './SideBar';
import styles from './static/styles.module.css'
import UserManagement from './UserManagement.js/Body'
import BookManagement from './BookManagement/Body'

import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useRouteMatch,
    useParams
} from "react-router-dom";

function Body(props) {

    return (
        <div className={styles.maxHeigh}>
            <Header />
            <div className={["row", styles.fullHeigh, styles.myContainer].join(" ")}>
                <div className="col-xs-2">
                    <SideBar />
                </div>
                <div className="col-xs-10">
                    <Switch>
                        <Route exact path="/dashboard">
                            <UserManagement />
                        </Route>
                        <Route path="/dashboard/book_management">
                            <BookManagement />
                        </Route>
                    </Switch>
                </div>
            </div>
        </div>
    );
}

export default Body;