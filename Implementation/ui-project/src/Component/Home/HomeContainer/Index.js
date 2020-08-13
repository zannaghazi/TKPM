import React from 'react';
import NewFeed from './NewFeed'
import HotBook from './HotBook'
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';
import { useState, useEffect } from 'react';
import config from '../../../asset/config.json'

function Index(props) {
    useEffect(() => {
        async function fetchAPI() {
            let urlTopNew = config.severAPi.hostUrl + ":8081/book/get_top_new";
            await fetch(urlTopNew, {
                method: "get",
                headers: { "Content-Type": "application/json" },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("newbook", data);
                    props.onSetListNewBookTitle(data);
                });

            let urlTopRent = config.severAPi.hostUrl + ":8081/book/get_top_renting";
            await fetch(urlTopRent, {
                method: "get",
                headers: { "Content-Type": "application/json" },
            })
                .then(res => res.json())
                .then(data => {
                    console.log("hotbook", data);
                    props.onSetListHotBookTitle(data);
                });
        }

        fetchAPI();

    }, []);

    return (
        <div>
            <NewFeed />
            <HotBook />
        </div>
    );
}

const mapStateToProps = state => {
    return {

    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetListNewBookTitle: (listBookTitle) => {
            dispatch(actions.setListNewBookTitle(listBookTitle));
        },
        onSetListHotBookTitle: (listBookTitle) => {
            dispatch(actions.setHotNewBookTitle(listBookTitle));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Index);