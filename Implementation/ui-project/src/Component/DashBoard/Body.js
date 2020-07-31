import React, { useEffect } from 'react';
import Header from './Header';
import SideBar from './SideBar';
import styles from './static/styles.module.css'
import UserManagement from './UserManagement.js/Body'
import BookManagement from './BookManagement/Body'
import PublishManagement from './PublishManagement/Body'
import {Container, Row, Col} from 'react-bootstrap'

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
            <Container className={[styles.myContainer].join(" ")}>
                <Row className = {[styles.maxHeigh, styles.myRow].join(" ")}>
                    <Col className={styles.myCol} sm={2}>
                        <SideBar />
                    </Col>
                    <Col className={styles.myCol} sm = {10}>
                        <Switch>
                            <Route exact path="/dashboard/user_management">
                                <UserManagement />
                            </Route>
                            <Route path="/dashboard/book_management">
                                <BookManagement />
                            </Route>
                            <Route path="/dashboard/publish_management">
                                <PublishManagement />
                            </Route>
                        </Switch>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Body;