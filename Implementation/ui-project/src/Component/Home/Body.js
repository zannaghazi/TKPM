import React, {useEffect} from 'react';
import HomeContainer from './HomeContainer/Index'
import BookDetail from './DetailBook/Index'
import ListRequestBook from './ListBook/Index'
import Header from './Header'
import styles from './static/styles.module.css'
import { connect } from 'react-redux';
import * as actions from '../../actions/index';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useRouteMatch,
    useParams
} from "react-router-dom";
import config from '../../asset/config.json'

function Body(props) {

    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8083/system/get_current_user";
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(data => {
                console.log("UserLogin", data);
                props.onSetUserLogin(data);
            });     
    }, []);

    return (
        <div className={styles.myBody}>
            <Header />
            <Switch>
                <Route exact path="/">
                    <HomeContainer />
                </Route>
                <Route path="/list_request" children={ListRequestBook} />
                <Route path="/book/:id" component={BookDetail} />
            </Switch>
            <hr className={styles.myHrTag} />
        </div>
    );
}

const mapStateToProps = state => {
    return {
        list: state.list_new_book_titles
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetUserLogin: (user) => {
            dispatch(actions.setUserLogin(user));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);