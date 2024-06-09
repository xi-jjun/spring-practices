class ApplicationController < ActionController::Base
  def hello
    render json: { greet: 'hello rails!' }
  end
end
