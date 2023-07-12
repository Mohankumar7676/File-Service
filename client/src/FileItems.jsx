import 'bootstrap/dist/css/bootstrap.min.css';  
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {Modal, Button} from 'react-bootstrap';  
import FileTracking from './FileTracking'
const FileItem = ({ file }) => {

    const [comment, setComment] = useState('');
    const [commented, setCommented] = useState(false);
    const [show, setShow] = useState(false);  
    
    const modalClose = () => setShow(false);  
    const modalShow = (value) => {
        setShow(true);
        setCommented(value);
    }
    const initial = file;
    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleAddComment  = async () => {
    let comments = file.operations.comments;
    comments.push({
            "user": localStorage.getItem("user"),
            "comment": comment,
            "date": new Date()
    });
    let toUpdate = file;
    toUpdate.operations.comments = comments;
    await axios.post('http://localhost:8081/create-fileOperations', toUpdate);
    setComment('');
    };

    return (
        <div>
        <h3>{file.fileName}</h3>
        <div className="file-content">
            {localStorage.getItem(file.fileName) ? (
            <>
                <br />
                <br />
                <textarea value={localStorage.getItem(file.fileName)} readOnly />
                <br />
                <br />
                <input
                type="text"
                placeholder="Add a comment"
                value={comment}
                onChange={handleCommentChange}
                />
                &nbsp;&nbsp;&nbsp;
                <button onClick={handleAddComment}>Add Comment</button>
                &nbsp;&nbsp;&nbsp;
                <Button variant="primary" onClick={()=>modalShow(true)}>View Comments</Button>
                &nbsp;&nbsp;&nbsp;
                <Button variant="primary" onClick={()=>modalShow(false)}>View Operations</Button>
                {show && <><Modal.Dialog>  
                    <Modal.Header closeButton>  
                        <Modal.Title>File Tracking</Modal.Title>  
                    </Modal.Header>  
                    <Modal.Body>  
                        {<FileTracking file={initial} isComment={commented}/>}
                    </Modal.Body>  
                    <Modal.Footer>  
                        <center><Button variant="secondary" onClick={modalClose}>Close</Button>  </center>
                    </Modal.Footer>  
                </Modal.Dialog>  
                </>
            }
                <br />
                <br />
                <hr />
            </>
            ) : (
                <>
            <p>No file content</p>
            <hr />
            </>
            )}
        </div>
        </div>
    );
};

export default FileItem;
