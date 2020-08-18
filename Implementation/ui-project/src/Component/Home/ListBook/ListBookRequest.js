import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './static/styles.module.css'
import { Card, Button } from 'react-bootstrap';
import { useLocation } from 'react-router-dom'
import QueryString from 'query-string'
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import MyTablePagination from './MyTablePagination';

function ListBookRequest(props) {
    let location = useLocation();
    const [item1, setItem1] = useState([]);
    const [item2, setItem2] = useState([]);
    const [active, setActive] = useState(1);
    const [listData, setListData] = useState([]);

    console.log(QueryString.parse(location.search));

    useEffect(() => {
        setItem1([]);
        setItem2([]);
        let dataPost = {
            type: QueryString.parse(location.search).type,
            key: QueryString.parse(location.search).key
        }
        let url = config.severAPi.hostUrl + ":8081/book/search";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dataPost)
        })
            .then(res => res.json())
            .then(data => {
                setListData(data);
                console.log(data);
                let temp = [];
                let limit = 0;
                if ((active - 1) * 8 + 8 > data.length) {
                    limit = data.length;
                }
                else {
                    limit = (active - 1) * 8 + 8;
                }
                console.log("limit", limit);
                for (let i = (active - 1) * 8; i < limit; i++) {
                    temp.push(data[i]);
                }
                console.log("data", temp);
                let cutIndex = 0;
                if (temp.length >= 4) {
                    cutIndex = 4;
                }
                else {
                    cutIndex = temp.length;
                }
                const listItems = temp.slice(0, 4).map((element, index) =>
                    <div className="col-sm-3" key={index}>
                        <Card style={{ width: '18rem' }} className={styles.containerHeight}>
                            <Card.Img variant="top" src={config.severAPi.hostUrl + ":8081" + element.path} className={styles.myImage} />
                            <Card.Body>
                                <Card.Title>{element.name}</Card.Title>
                                <Card.Text>
                                    {element.author}
                                </Card.Text>
                                <Card.Text>
                                    {element.releaseDate}
                                </Card.Text>
                                <Button variant="primary">Xem chi tiết</Button>
                            </Card.Body>
                        </Card>
                    </div>
                );
                setItem1(listItems);
                let listItems2 = [];
                if (temp.length > 4) {
                    listItems2 = temp.slice(4).map((element, index) =>
                        <div className="col-sm-3" key={index} >
                            <Card style={{ width: '18rem' }} className={styles.containerHeight}>
                                <Card.Img variant="top" src={config.severAPi.hostUrl + ":8081" + element.path} className={styles.myImage} />
                                <Card.Body>
                                    <Card.Title>{element.name}</Card.Title>
                                    <Card.Text>
                                        {element.author}
                                    </Card.Text>
                                    <Card.Text>
                                    {element.releaseDate}
                                </Card.Text>
                                    <Button variant="primary">Xem chi tiết</Button>
                                </Card.Body>
                            </Card>
                        </div>
                    );
                    setItem2(listItems2);
                }
            });
    }, [location, active]);

    return (
        <div>
            <div className={styles.myTitle}><span className={styles.myText}>{QueryString.parse(location.search).key}</span></div>
            <div className="row">
                {item1}
            </div>
            {item2.length > 0 ? <div className="row mt-5">{item2}</div> : null}
            {listData.length > 0 ? <div className="d-flex justify-content-center mt-3">
                <MyTablePagination active={active} listBookTitle={listData} setActive={setActive} />
            </div> : <div>Không có dữ liệu</div>}

        </div>
    );
}

const mapStateToProps = state => {
    return {
        list_types: state.type_books
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListBookRequest);