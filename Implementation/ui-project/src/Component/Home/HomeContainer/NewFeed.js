import React from 'react';
import styles from './static/styles.module.css'
import MyCarousel from './MyCarousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Button } from 'react-bootstrap';
import * as actions from '../../../actions/index';
import { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import config from '../../../asset/config.json'
import { useHistory } from "react-router-dom";

function NewFeed(props) {
    const [carouselList, setCarouseList] = useState([]);
    const history = useHistory();

    function detailBook(item){
        history.push("/book/"+ item.ISBN);
    }

    const listNormal = props.list.slice(3).map((element, index) =>
        <div className="col-sm-4" key ={index}>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src={config.severAPi.hostUrl + ":8081" + element.path} className={styles.myImage} />
                <Card.Body>
                    <Card.Title>{element.releaseDate}</Card.Title>
                    <Card.Text>
                        {element.author}
                    </Card.Text>
                    <Button variant="primary" onClick={() => detailBook(element)}>Xem chi tiết</Button>
                </Card.Body>
            </Card>
        </div>
    );

    useEffect(() => {
        console.log("useEffect", props.list)
        setCarouseList(props.list.slice(0, 3));
    }, [props.list]);

    return (
        <div>
            <div className={styles.myTitle}><span className={styles.myText}>Sách mới</span></div>
            <div className="row">
                <div className="col-sm-3">
                    <MyCarousel className={styles.myMarginTop} listData={carouselList} />
                </div>
                <div className="col-sm-9">
                    <div className="row">
                        {listNormal}
                    </div>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = state => {
    return {
        list: state.list_new_book_titles
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(NewFeed);