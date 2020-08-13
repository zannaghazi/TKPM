import React, { useEffect } from 'react';
import Header from './Header';
import SideBar from './SideBar';
import styles from './static/styles.module.css'
import UserManagement from './UserManagement.js/Body'
import BookManagement from './BookManagement/Body'
import PublishManagement from './PublishManagement/Body'
import AuthorManagement from './AuthorManagement/Body'
import BookTitleManagement from './TitleBookManagement/Body'
import {Container, Row, Col} from 'react-bootstrap'
import AccountManagement from './AccountManagement/Body'
import NewAccount from './NewAccount/Body'
import NewBookTitle from './NewBookTitle/Body'
import NewBook from './NewBook/Body'
import NewAuthor from './NewAuthor/Body'
import EditBookTitle from './EditTitleBook/Body'

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
                            <Route path="/dashboard/author_management">
                                <AuthorManagement />
                            </Route>
                            <Route path="/dashboard/booktitle_management">
                                <BookTitleManagement />
                            </Route>
                            <Route path="/dashboard/new_account">
                                <NewAccount />
                            </Route>
                            <Route path="/dashboard/new_book_title">
                                <NewBookTitle />
                            </Route>
                            <Route path="/dashboard/new_book">
                                <NewBook />
                            </Route>
                            <Route path="/dashboard/new_author">
                                <NewAuthor />
                            </Route>
                            <Route path="/dashboard/edit_book_title">
                                <EditBookTitle />
                            </Route>
                            <Route path="/dashboard">
                                <AccountManagement />
                            </Route>
                        </Switch>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Body;