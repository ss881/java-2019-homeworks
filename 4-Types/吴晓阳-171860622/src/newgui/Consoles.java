package newgui;

import item.DisplayableItem;
import item.DrawableItem;
import item.Item;
import itemEventProcessor.ItemEventProcessor;

import java.awt.*;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consoles {
    public static Console UnmodifiableConsole(Console console){
        return new UnmodifiableConsole(console);
    }

    public static Console SynchronizedConsole(Console console){
        return new SynchronizedConsole(console);
    }

    private static class UnmodifiableConsole implements Console{
        private final Console console;

        UnmodifiableConsole(Console console){
            this.console = console;
        }

        @Override
        public int getGridRowNum() {
            return console.getGridRowNum();
        }

        @Override
        public int getGridColNum() {
            return console.getGridColNum();
        }

        @Override
        public Dimension getGridSize() {
            return console.getGridSize();
        }

        @Override
        public Collection<DisplayableItem> getGridItems(int xPos, int yPos) {
            return console.getGridItems(xPos, yPos);
        }

        @Override
        public Rectangle getItemPos(Item item) {
            return console.getItemPos(item);
        }

        @Override
        public Dimension getScreenSize() {
            return console.getScreenSize();
        }

        @Override
        public void addItem(DisplayableItem item, int xItemPos, int yItemPos) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addItem(DisplayableItem item, int xItemPos, int yItemPos, int itemWidth, int itemHeight) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int addItemEventProcessor(ItemEventProcessor processor) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeItemEventProcessor(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            console.clear();
        }

        @Override
        public void display() {
            console.display();
        }
    }

    private static class SynchronizedConsole implements Console{
        private final Console console;

        private final Lock lock = new ReentrantLock();

        public SynchronizedConsole(Console console){
            this.console = console;
        }

        @Override
        public int getGridRowNum() {
            lock.lock();
            int res = console.getGridRowNum();
            lock.unlock();
            return res;
        }

        @Override
        public int getGridColNum() {
            lock.lock();
            int res = console.getGridColNum();
            lock.unlock();
            return res;
        }

        @Override
        public Dimension getGridSize() {
            lock.lock();
            Dimension res = console.getGridSize();
            lock.unlock();
            return res;
        }

        @Override
        public Collection<DisplayableItem> getGridItems(int xPos, int yPos) {
            lock.lock();
            Collection<DisplayableItem> res = console.getGridItems(xPos, yPos);
            lock.unlock();
            return res;
        }

        @Override
        public Rectangle getItemPos(Item item) {
            return console.getItemPos(item);
        }

        @Override
        public Dimension getScreenSize() {
            return console.getScreenSize();
        }

        @Override
        public void addItem(DisplayableItem item, int xItemPos, int yItemPos) {
            lock.lock();
            console.addItem(item, xItemPos, yItemPos);
            lock.unlock();
        }

        @Override
        public void addItem(DisplayableItem item, int xItemPos, int yItemPos, int itemWidth, int itemHeight) {
            lock.lock();
            console.addItem(item, xItemPos, yItemPos, itemWidth, itemHeight);
            lock.unlock();
        }

        @Override
        public int addItemEventProcessor(ItemEventProcessor processor) {
            lock.lock();
            int index = console.addItemEventProcessor(processor);
            lock.unlock();
            return index;
        }

        @Override
        public void removeItemEventProcessor(int index) {
            lock.lock();
            console.removeItemEventProcessor(index);
            lock.unlock();
        }

        @Override
        public void clear() {
            console.clear();
        }

        @Override
        public void display() {
            lock.lock();
            console.display();
            lock.unlock();
        }
    }
}
