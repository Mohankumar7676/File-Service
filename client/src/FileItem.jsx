import React, { useState } from 'react';
import axios from 'axios';
import "./styles.css";

const FileItem = ({ file }) => {
const [comment, setComment] = useState('');

const handleCommentChange = (e) => {
    setComment(e.target.value);
};

const handleAddComment = async () => {
    try {
    await axios.post(`http://localhost:8080/file/comment/${file.id}`, { comment });
    setComment('');
    } catch (error) {
    console.error('Error:', error);
    alert('Adding comment failed. Please try again.');
    }
};

return (
    <div className="uploaded-view">
    <div className="view-modal">
    <h3>FileName: {file.fileName}</h3>
    <input type="text" placeholder="Add a comment" value={comment} onChange={handleCommentChange} />
    &nbsp;&nbsp;
    <button className="button" onClick={handleAddComment}>Add Comment</button>
    &nbsp;&nbsp;
    <button className="button" onClick={handleAddComment}>View file</button>
    </div>
    </div>
);
};

export default FileItem;
