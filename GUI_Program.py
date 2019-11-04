#!/usr/bin/env python
# coding: utf-8

# In[17]:


import tkinter


# In[18]:


r = tk.Tk()
r.title('Enoding and Decoding')

def encodingBox():
    tkMessageBox.showinfo('Encoding')
    imageToImage = tk.Button(r, text ='Image to Image',width = 25, command = encodingBox)
    textToImage = tk.Button(r, text ='Text to Image',width = 25, command = encodingBox)

def decodingBox():
    tkMessageBox.showinfo('Decoding')
    imageFromImage = tk.Button(r, text ='Image from Image',width = 25, command = encodingBox)
    textFromImage = tk.Button(r, text ='Text from Image',width = 25, command = encodingBox)
    
encoding = tk.Button(r, text ='Encoding',width = 25, command = encodingBox)
decoding = tk.Button(r, text ='Decoding',width = 25, command = decodingBox)

encoding.pack()
decoding.pack()
r.mainloop()


# In[ ]:




