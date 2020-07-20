import React from 'react';
import NavBar from './NavBar'
import NewFeed from './NewFeed'
import HotBook from './HotBook'
import Header from './Header'
import styles from './static/styles.module.css'
import Footer from './Footer'

function Home(props) {
    return (
        <div>
            <NavBar />

            <div className={styles.myBody}>
                <Header />
                <NewFeed />
                <HotBook />
            </div>
            <hr className = {styles.myHrTag}/>
            <Footer className={styles.myMarginTop}/>
        </div>
    );
}

export default Home;