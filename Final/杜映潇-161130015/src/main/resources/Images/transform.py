from PIL import Image

img = Image.open('Blue.png')
new_img = img.convert('RGB')
new_img.save('Blue.jpeg', 'jpeg')