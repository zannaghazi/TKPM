import React, { useEffect } from 'react';
import Header from './Header';
import SideBar from './SideBar';
import styles from './static/styles.module.css'
import UserManagement from './UserManagement.js/Body'
import BookManagement from './BookManagement/Body'
import PublishManagement from './PublishManagement/Body'
import AuthorManagement from './AuthorManagement/Body'
import BookTitleManagement from './TitleBookManagement/Body'
import { Container, Row, Col } from 'react-bootstrap'
import AccountManagement from './AccountManagement/Body'
import NewAccount from './NewAccount/Body'
import NewBookTitle from './NewBookTitle/Body'
import NewBook from './NewBook/Body'
import NewAuthor from './NewAuthor/Body'
import EditBookTitle from './EditTitleBook/Body'
import EditAuthor from './EditAuthor/Body'
import { connect } from 'react-redux';
import * as actions from '../../actions/index';
import config from '../../asset/config.json'
import NewPublisher from './NewPublisher/Body'
import EditPublisher from './EditPublisher/Body'
import Report from './Report/Body'
import SystemManagement from './SystemManagement/Body'
import EditAccount from './EditAccount/Body'
import NewRentingBooKBill from './NewRentingBookBill/Body'


import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useRouteMatch,
    useParams
} from "react-router-dom";

function Body(props) {
    useEffect(() => {
        async function fetchAPI() {
            let urlAuthor = config.severAPi.hostUrl + ":8081/book/get_all_author";
            await fetch(urlAuthor, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("author", data);
                    props.onSetListAuthor(data);
                });

            let urlUserLogin = config.severAPi.hostUrl + ":8083/system/get_current_user";
            await fetch(urlUserLogin, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("UserLogin", data);
                    props.onSetUserLogin(data);
                });

            let urlPublisher = config.severAPi.hostUrl + ":8081/book/get_all_publisher";
            await fetch(urlPublisher, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("publisher", data);
                    props.onSetListPublisher(data);
                });

            let urlRole = config.severAPi.hostUrl + ":8083/system/get_all_role";
            await fetch(urlRole, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("type", data);
                    props.onSetListRole(data);
                });

            let urlTypeBooks = config.severAPi.hostUrl + ":8081/book/get_all_type";
            await fetch(urlTypeBooks, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("type", data);
                    props.onSetListTypeBook(data);
                });

            let urlSystem = config.severAPi.hostUrl + ":8083/system/get_all_system_const";
            fetch(urlSystem, {
                method: "get",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            })
                .then(res => res.json())
                .then(data => {
                    props.onSetSystem(data);
                });
        }

        fetchAPI();
    }, []);

    return (
        <div className={styles.maxHeigh}>
            <Header />
            <Container className={[styles.myContainer].join(" ")}>
                <Row className={[styles.maxHeigh, styles.myRow].join(" ")}>
                    <Col className={styles.myCol} sm={2}>
                        <SideBar />
                    </Col>
                    <Col className={styles.myCol} sm={10}>
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
                            <Route path="/dashboard/new_publisher">
                                <NewPublisher />
                            </Route>
                            <Route path="/dashboard/edit_author/:id">
                                <EditAuthor />
                            </Route>
                            <Route path="/dashboard/edit_publisher/:id">
                                <EditPublisher />
                            </Route>
                            <Route path="/dashboard/edit_book_title/:isbn" component={EditBookTitle}>
                            </Route>
                            <Route path="/dashboard/edit_account/:id" component={EditAccount}>
                            </Route>
                            <Route path="/dashboard/new_renting_book_bill/:id" component={NewRentingBooKBill}>
                            </Route>
                            <Route path="/dashboard/report">
                                <Report />
                            </Route>
                            <Route path="/dashboard/system_management">
                                <SystemManagement />
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

const mapStateToProps = state => {
    return {

    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetListAuthor: (item) => {
            dispatch(actions.setListAuthor(item));
        },
        onSetUserLogin: (user) => {
            dispatch(actions.setUserLogin(user));
        },
        onSetListPublisher: (item) => {
            dispatch(actions.setListPublisher(item));
        },
        onSetListTypeBook: (listTypeBook) => {
            dispatch(actions.setListTypeBook(listTypeBook));
        },
        onSetListRole: (listRole) => {
            dispatch(actions.setListRole(listRole));
        },
        onSetSystem: (system) => {
            dispatch(actions.setSystem(system));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);