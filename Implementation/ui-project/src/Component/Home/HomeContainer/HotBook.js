import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './static/styles.module.css'
import { Card, Button } from 'react-bootstrap';
import { connect } from 'react-redux';
import config from '../../../asset/config.json'
import { useHistory } from "react-router-dom";

function HotBook(props) {
    const history = useHistory();
    function detailBook(item){
        history.push("/book/"+ item.ISBN);
    }

    const listHotBook = props.list.map((element, index) =>
        <div className="col-sm-3" key={index}>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src={config.severAPi.hostUrl + ":8081" + element.path} className={styles.myImage} />
                <Card.Body>
                    <Card.Title>{element.releaseDate}</Card.Title>
                    <Card.Text>
                        {element.author}.
            </Card.Text>
                    <Button variant="primary" onClick={() => detailBook(element)}>Xem chi tiết</Button>
                </Card.Body>
                <div className={styles.mySpaner}>{element.rentingCount} lượt</div>
            </Card>
        </div>
    );

    return (
        <div className="mt-5">
            <div className={styles.myHotBookTitle}><span className={styles.myTitleHotBook}>Sách hot</span></div>
            <div className="row">
                {listHotBook}
            </div>
        </div>
    );
}

const mapStateToProps = state => {
    return {
        list: state.list_hot_book_titles
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HotBook);