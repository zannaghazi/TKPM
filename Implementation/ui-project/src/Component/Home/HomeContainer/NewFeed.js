import React from 'react';
import styles from './static/styles.module.css'
import MyCarousel from './MyCarousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Button } from 'react-bootstrap';
import Image4 from '../../../asset/picture/srcTest/sach4.jpg'
import Image5 from '../../../asset/picture/srcTest/sach5.jpg'
import Image6 from '../../../asset/picture/srcTest/sach6.jpg'

function NewFeed(props) {
    return (
        <div>
            <div className={styles.myTitle}><span className={styles.myText}>Sách mới</span></div>
            <div className="row">
                <div className="col-sm-3">
                    <MyCarousel className={styles.myMarginTop} />
                </div>
                <div className="col-sm-9">
                    <div className="row">
                        <div className="col-sm-4">
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
                            </Card>
                        </div>
                        <div className="col-sm-4">
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
                            </Card>
                        </div>
                        <div className="col-sm-4">
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
                            </Card>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default NewFeed;