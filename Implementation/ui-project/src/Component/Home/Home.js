import React from 'react';
import NavBar from './NavBar'
import styles from './static/styles.module.css'
import Footer from './Footer'
import HomeContainer from './HomeContainer/Index'
import BookDetail from './DetailBook/Index'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useRouteMatch,
    useParams
} from "react-router-dom";

function Home(props) {
    return (
        <div>
            <NavBar />
            <Switch>
                <Route exact path="/">
                    <HomeContainer/>
                </Route>
                <Route path="/book/:id" component={BookDetail}/>
            </Switch>
            <hr className={styles.myHrTag} />
            <Footer className={styles.myMarginTop} />
        </div>
    );
}

export default Home;