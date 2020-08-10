import React from 'react';
import NavBar from './NavBar'
import styles from './static/styles.module.css'
import Footer from './Footer'
import Body from './Body'

function Home(props) {
    return (
        <div>
            <NavBar />
            <Body />

            <Footer className={styles.myMarginTop} />
        </div>
    );
}

export default Home;