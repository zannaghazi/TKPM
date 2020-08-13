import React from 'react';
import HomeContainer from './HomeContainer/Index'
import BookDetail from './DetailBook/Index'
import ListRequestBook from './ListBook/Index'
import Header from './Header'
import styles from './static/styles.module.css'
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

export default Body;