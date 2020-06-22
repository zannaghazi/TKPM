import React from 'react';
import NavBar from './NavBar'
import NewFeed from './NewFeed'
import styles from './static/styles.module.css'

function Home(props) {
    return (
        <div>
            <NavBar />
            <div className={styles.myBody}>
                <NewFeed />
            </div>
        </div>
    );
}

export default Home;