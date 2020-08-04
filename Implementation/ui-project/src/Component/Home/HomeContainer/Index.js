import React from 'react';
import styles from './static/styles.module.css'
import NewFeed from './NewFeed'
import HotBook from './HotBook'
import Header from './Header'

function Index(props) {
    return (
        <div className={styles.myBody}>
            <Header />
            <NewFeed />
            <HotBook />
        </div>
    );
}

export default Index;