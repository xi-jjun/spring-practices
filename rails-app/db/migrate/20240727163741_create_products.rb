class CreateProducts < ActiveRecord::Migration[7.1]
  def change
    create_table :products do |t|
      t.string :name
      t.string :code
      t.integer :price
      t.integer :stock
      t.boolean :enable

      t.timestamps
    end
  end
end
