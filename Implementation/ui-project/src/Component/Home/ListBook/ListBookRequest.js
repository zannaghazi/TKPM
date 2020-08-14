import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './static/styles.module.css'
import Image4 from '../../../asset/picture/srcTest/sach4.jpg'
import Image5 from '../../../asset/picture/srcTest/sach5.jpg'
import Image6 from '../../../asset/picture/srcTest/sach6.jpg'
import { Card, Button } from 'react-bootstrap';
import { useLocation } from 'react-router-dom'
import QueryString from 'query-string'

function ListBookRequest(props) {
    let location = useLocation();

    console.log(QueryString.parse(location.search));
    return (
        <div className="mt-5">
            <div className={styles.myTitle}><span className={styles.myText}>Sách mới</span></div>
            <div className="row">
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image4} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image5} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image6} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image6} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
            </div>
            <div className="row mt-5">
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image4} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image5} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image6} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
                <div className="col-sm-3">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Image6} className={styles.myImage} />
                        <Card.Body>
                            <Card.Title>22/06/2020</Card.Title>
                            <Card.Text>
                                Some quick example text to build on the card title and make up the bulk of
                                the card's content.
                                    </Card.Text>
                            <Button variant="primary">Xem chi tiết</Button>
                        </Card.Body>
                        <div className={styles.mySpaner}>8 lượt</div>
                    </Card>
                </div>
            </div>
        </div>
    );
}

export default ListBookRequest;