import React, {useEffect} from 'react';
import Header from './Header';
import SideBar from './SideBar';
import styles from './static/styles.module.css'

function Body(props) {

    return (
        <div className = {styles.maxHeigt}>
            <Header />
            <SideBar />

        </div>
    );
}

export default Body;