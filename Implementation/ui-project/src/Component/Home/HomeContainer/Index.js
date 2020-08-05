import React from 'react';
import styles from './static/styles.module.css'
import NewFeed from './NewFeed'
import HotBook from './HotBook'

function Index(props) {
    return (
        <div className={styles.myBody}>
            <NewFeed />
            <HotBook />
        </div>
    );
}

export default Index;