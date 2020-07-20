import React from 'react';
import NavBar from './NavBar'
import NewFeed from './NewFeed'
import HotBook from './HotBook'
import Header from './Header'
import styles from './static/styles.module.css'

function Home(props) {
    return (
        <div>
            <NavBar />

            <div className={styles.myBody}>
                <Header />
                <NewFeed />
                <HotBook />
            </div>
        </div>
    );
}

export default Home;